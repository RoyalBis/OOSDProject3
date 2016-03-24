package Business;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Product implements IEntity
{
    private int ProductId;
    private String ProdName;

    public int getProductId() { return ProductId; }

    public void setProductId(int productId)
    {
        ProductId = productId;
    }

    public String getProdName()
    {
        return ProdName;
    }

    public void setProdName(String prodName)
    {
        ProdName = prodName;
    }

    @Override
    public String toString()
    {
        return ProdName; 
    }
}
