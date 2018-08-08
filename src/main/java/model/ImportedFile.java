package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file")
public class ImportedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "import_date")
    private Date importDate;

    public ImportedFile() {
    }

    public ImportedFile(String fileName, Date importDate) {
        this.fileName = fileName;
        this.importDate = importDate;
    }

    public ImportedFile(Long id, String fileName, Date importDate) {
        this.id = id;
        this.fileName = fileName;
        this.importDate = importDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}
