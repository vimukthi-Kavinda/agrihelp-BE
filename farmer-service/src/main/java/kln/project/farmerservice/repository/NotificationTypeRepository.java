package kln.project.farmerservice.repository;


import kln.project.farmerservice.model.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationTypeRepository extends JpaRepository<NotificationType,String>, JpaSpecificationExecutor<NotificationType> {
}
