package kln.project.customerservice.repository;


import kln.project.customerservice.model.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationTypeRepository extends JpaRepository<NotificationType,String>, JpaSpecificationExecutor<NotificationType> {
}
