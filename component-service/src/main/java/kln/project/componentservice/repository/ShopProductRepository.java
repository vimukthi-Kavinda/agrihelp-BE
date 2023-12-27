package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.ShopProduct;
import kln.project.componentservice.model.entity.ShopProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct, ShopProductId>, JpaSpecificationExecutor<ShopProduct> {

    @Query(value = "select sp.product_id ,p.product_name from shop_product sp join  product p on p.product_id =sp.product_id join shop sh on sh.shop_reg_no =sp.shop_reg_no \n" +
            "where sh.owner_nic =?1 and sp.shop_reg_no <> ?2 ",nativeQuery = true)
    List<Object[]> getAllProductsOwn(String ownerNic, String shopNo);
}