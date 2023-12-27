package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopRepository extends JpaRepository<Shop, String>, JpaSpecificationExecutor<Shop> {
}