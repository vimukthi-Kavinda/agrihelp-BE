package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.ShopProduct;
import kln.project.customerservice.model.entity.ShopProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopProductRepository extends JpaRepository<ShopProduct, ShopProductId>, JpaSpecificationExecutor<ShopProduct> {
}