package mother;

import com.price.domain.model.Brand;

public class BrandMother {
    public static Brand generateBrand() {
        Brand brand = new Brand();
        brand.setBrandId(1L);
        brand.setBrandName("ZARA");
        return brand;
    }

}
