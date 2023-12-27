package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.ShopOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopOwnerRepository extends JpaRepository<ShopOwner, String>, JpaSpecificationExecutor<ShopOwner> {
}