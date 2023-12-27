package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Product;
import kln.project.farmerservice.model.entity.Shop;
import kln.project.farmerservice.model.entity.ShopProduct;
import kln.project.farmerservice.model.entity.ShopProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct, ShopProductId>, JpaSpecificationExecutor<ShopProduct> {
    List<ShopProduct> findByShopproductidProduct(Product product);

    List<ShopProduct> findByShopproductidShop(Shop shop);
}