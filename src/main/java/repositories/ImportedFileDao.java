package repositories;

import model.ImportedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImportedFileDao extends JpaRepository<ImportedFile, Long> {

    Optional<ImportedFile> getFileByFileName(@Param("fileName") String fileName);
}
