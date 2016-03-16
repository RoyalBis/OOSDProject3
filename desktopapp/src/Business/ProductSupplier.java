package Business;

/**
 * Created by 723403 on 3/15/2016.
 */
public class ProductSupplier implements IEntity
{
    private int ProductSupplierId;
    private Product product;
    private Supplier supplier;

    public int getProductSupplierId()
    {
        return ProductSupplierId;
    }

    public void setProductSupplierId(int productSupplierId)
    {
        ProductSupplierId = productSupplierId;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }

    @Override
    public String toString()
    {
        return "ProductSupplier{" +
                "ProductSupplierId=" + ProductSupplierId +
                ", product=" + product +
                ", supplier=" + supplier +
                '}';
    }
}
