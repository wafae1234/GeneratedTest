package ma.dxc.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import ma.dxc.dto.AuditDTO;
import ma.dxc.orchestration.AuditOrchestration;

@SpringBootTest
public class AuditTest {
	@Autowired
	private AuditOrchestration auditOrchestration;
	
	@Test
	void testSearchForAuditsWithTwoKeywords() {
		Page<AuditDTO> audits = auditOrchestration.searchTwoKeywords("2020-05-04", "2020-05-04", 0, 5, "date");
		
		assertThat(audits).isNotNull();
		//assertThat(audits.getContent().get(0)).isInstanceOf(AuditDTO);
		assertThat(audits.getContent().get(0).getId()).isEqualTo(323);
		//assertThat(audits.)
	}

}
