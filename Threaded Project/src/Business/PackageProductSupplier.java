package Business;
/**
 * Created by 723403 on 3/15/2016.
 */
public class PackageProductSupplier implements IEntity
{
    private int PackageId;
    private ProductSupplier productsupplier;

    public int getPackageId(){
        return PackageId;
    }

    public void setPackageId(int packageId){
        PackageId = packageId;
    }

    public ProductSupplier getProductsupplier(){
        return productsupplier;
    }

    public void setProductsupplier(ProductSupplier productsupplier) {
        this.productsupplier = productsupplier;
    }

    @Override
    public String toString()
    {
        return  productsupplier.toString();
    }
}
