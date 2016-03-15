package Business;

/**
 * Created by 723403 on 3/9/2016.
 */
public class Supplier implements IEntity
{
    private int SupplierId;
    private String SupName;

    public int getSupplierId()
    {
        return SupplierId;
    }

    public void setSupplierId(int supplierId)
    {
        SupplierId = supplierId;
    }

    public String getSupName() { return SupName; }

    public void setSupName(String supName)
    {
        SupName = supName;
    }

    @Override
    public String toString()
    {
        return "Supplier{" +
                "SupplierId=" + SupplierId +
                ", SupName='" + SupName + '\'' +
                '}';
    }
}
