package unit.domain.model;

import com.price.domain.model.Brand;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BrandTest {

    @Test
    void testBrandProperties() {
        // Given
        Long brandId = 1L;
        String brandName = "ZARA";
        // When
        Brand brand = new Brand();
        brand.setBrandId(brandId);
        brand.setBrandName(brandName);
        // Then
        assertThat(brand.getBrandId()).isEqualTo(brandId);
        assertThat(brand.getBrandName()).isEqualTo(brandName);
    }
}
