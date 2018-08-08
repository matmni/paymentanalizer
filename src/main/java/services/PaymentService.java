package services;

import Exceptions.FileImportedException;
import Exceptions.ImportFileErrorsContainer;
import Exceptions.ParseError;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ImportedFileDao;
import repositories.PaymentDao;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class PaymentService {
    private ImportedFileDao importedFileDao;
    private PaymentDao paymentDao;
    private CompanyService companyService;

    @Autowired
    public PaymentService(ImportedFileDao importedFileDao, PaymentDao paymentDao, CompanyService companyService) {
        this.importedFileDao = importedFileDao;
        this.paymentDao = paymentDao;
        this.companyService = companyService;
    }

    public IntervalResults calculatePayments(Date fromDate, Date toDate) {
        List<CompanyPaymentResult> paymentResults = new ArrayList<>();
        List<Payment> notMappedPayments = new ArrayList<>();
        IntervalResults results = new IntervalResults();

        List<Payment> payments = paymentDao.findAllByPayDateBetween(fromDate, toDate);
        if (payments.isEmpty()) {
            return results;
        }

        List<Company> companies = companyService.getAllCompanies();
        for (Company company : companies) {
            createNewPaymentResult(paymentResults, company);
        }
        for (Company company : companies) {
            String[] keyWords = company.getKeyWords().split(",");
            for (Payment payment : payments) {
                boolean contains = true;
                contains = checkIfPaymentContainsKewWords(keyWords, payment);
                if (contains) {
                    preparePaymentResult(paymentResults, company, payment);
                }
            }
        }

        for (Payment payment : payments) {
            boolean contains = false;
            for (CompanyPaymentResult paymentResult : paymentResults) {
                if (checkIfPaymentContainsKewWords(paymentResult.getKeyWords().split(","), payment)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                notMappedPayments.add(payment);
            }
        }

        results.setCompanyPaymentResults(paymentResults);
        results.setNotMappedPayments(notMappedPayments);
        return results;
    }

    private void preparePaymentResult(List<CompanyPaymentResult> paymentResults, Company company, Payment payment) {
        boolean paymentResultUpdated = false;
        for (CompanyPaymentResult paymentResult : paymentResults) {
            if (paymentResult.getKeyWords().equals(company.getKeyWords())) {
                paymentResultUpdated = updatePaymentResult(payment, paymentResult);
            }
        }
    }


    private boolean updatePaymentResult(Payment payment, CompanyPaymentResult paymentResult) {
        boolean paymentResultUpdated;
        paymentResult.setRest(paymentResult.getRest().subtract(payment.getAmount()));
        paymentResultUpdated = true;
        return paymentResultUpdated;
    }

    private boolean checkIfPaymentContainsKewWords(String[] keyWords, Payment payment) {
        for (String key : keyWords) {
            if (!payment.getDescription().contains(key)) {
                return false;
            }
        }
        return true;
    }

    private void createNewPaymentResult(List<CompanyPaymentResult> paymentResults, Company company) {
        CompanyPaymentResult paymentResult = new CompanyPaymentResult();
        paymentResult.setCompanyName(company.getName());
        paymentResult.setDescription(company.getDescription());
        paymentResult.setKeyWords(company.getKeyWords());
        paymentResult.setRentAmount(company.getAmount());
        paymentResult.setRest(company.getAmount());
        paymentResults.add(paymentResult);
    }

    public ImportFileErrorsContainer importDataFile(File file) throws FileImportedException, IOException {
        validateIfFileWasRead(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("ISO-8859-2")));
        List<Payment> payments = new ArrayList<>();

        ImportFileErrorsContainer errorsContener = readFile(reader, payments);

        if (!errorsContener.hasErrors()) {
            paymentDao.save(payments);
            importedFileDao.save(new ImportedFile(file.getName(), new Date()));
        }
        return errorsContener;
    }

    private ImportFileErrorsContainer readFile(BufferedReader reader, List<Payment> payments) {
        ImportFileErrorsContainer errorsContener = new ImportFileErrorsContainer();
        try (Stream<String> stream = reader.lines().skip(1)) {
            stream.forEach(row -> {
                try {
                    payments.add(parse(row));
                } catch (Exception e) {
                    errorsContener.getMessages().add(new ParseError(e.toString(), row));
                    e.printStackTrace();
                }
            });
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return errorsContener;
    }

    private Payment parse(String row) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] splittedRow = row.split(Pattern.quote(";"));
        Payment payment = new Payment();
        payment.setPayDate(sdf.parse(splittedRow[0].replaceAll(Pattern.quote("\""), "")));
        payment.setPostDate(sdf.parse(splittedRow[1].replaceAll("\"", "")));
        payment.setDescription(splittedRow[2]);
        payment.setAmount(new BigDecimal(splittedRow[3].replaceAll(Pattern.quote("\""), "").replaceAll(",", ".").replace("\u00A0", "").replace(" PLN", "")));

        return payment;
    }

    private void validateIfFileWasRead(File file) throws FileImportedException {
        Optional<ImportedFile> existingFile = importedFileDao.getFileByFileName(file.getName());
        if (existingFile.isPresent()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            throw new FileImportedException("Plik o takiej nazwie był zaczytany: " + sdf.format(existingFile.get().getImportDate()));
        }
    }
}
