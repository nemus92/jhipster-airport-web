package com.myairport.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.myairport.web.rest.TestUtil;

public class AirplaneTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Airplane.class);
        Airplane airplane1 = new Airplane();
        airplane1.setId(1L);
        Airplane airplane2 = new Airplane();
        airplane2.setId(airplane1.getId());
        assertThat(airplane1).isEqualTo(airplane2);
        airplane2.setId(2L);
        assertThat(airplane1).isNotEqualTo(airplane2);
        airplane1.setId(null);
        assertThat(airplane1).isNotEqualTo(airplane2);
    }
}
