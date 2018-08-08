package repositories;

import model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Long> {

    List<Payment> findAllByPayDateBetween(@Param("start") Date start, @Param("end") Date end);
}
