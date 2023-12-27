package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.ShopProduct;
import kln.project.marketservice.model.entity.ShopProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShopProductRepository extends JpaRepository<ShopProduct, ShopProductId>, JpaSpecificationExecutor<ShopProduct> {
}