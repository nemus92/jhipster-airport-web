package com.myairport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.myairport.web.rest.TestUtil;

public class AirportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Airport.class);
        Airport airport1 = new Airport();
        airport1.setId(1L);
        Airport airport2 = new Airport();
        airport2.setId(airport1.getId());
        assertThat(airport1).isEqualTo(airport2);
        airport2.setId(2L);
        assertThat(airport1).isNotEqualTo(airport2);
        airport1.setId(null);
        assertThat(airport1).isNotEqualTo(airport2);
    }
}
