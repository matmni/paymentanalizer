package services;

import model.ImportedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ImportedFileDao;

import java.util.List;

@Service
public class ImportedFileService {

    private ImportedFileDao importedFileDao;

    @Autowired
    public ImportedFileService(ImportedFileDao importedFileDao) {
        this.importedFileDao = importedFileDao;
    }

    public List<ImportedFile> getAllFiles() {
        return importedFileDao.findAll();
    }
}
