package services;

import model.Company;
import model.CompanyTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CompanyDao;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CompanyService {

    private CompanyDao dao;

    @Autowired
    public CompanyService(CompanyDao dao) {
        this.dao = dao;
    }

    public void addNewCompany(Company company) {
        System.out.println(company.toString());
        dao.save(company);
    }

    public List<Company> getAllCompanies() {
        return dao.findAll();
    }

    public void updateCompany(CompanyTableData row) {
        Company company = new Company(row.getId(), row.getName(), row.getDescription(), BigDecimal.valueOf(row.getAmount()), row.getKeyWords());
        dao.save(company);
    }

    public void deteCompany(CompanyTableData row) {
        Company company = new Company(row.getId(), row.getName(), row.getDescription(), BigDecimal.valueOf(row.getAmount()), row.getKeyWords());
        dao.delete(company);
    }
}
