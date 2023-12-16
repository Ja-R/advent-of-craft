package document;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static document.DocumentTemplateType.fromDocumentTypeAndRecordType;

class DocumentTemplateTypeTest {

    @Test
    void fails_when_document_type_does_not_exist() {
        Assertions.assertThatThrownBy(() -> fromDocumentTypeAndRecordType("invalidType", "IndividualPersonProspect"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Document template type or record type");

    }

    @Test
    void fails_when_record_type_does_not_exist() {
        Assertions.assertThatThrownBy(() -> fromDocumentTypeAndRecordType("DEER", "invalidRecordType"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Document template type or record type");

    }

    @ParameterizedTest
    @MethodSource("validTypes")
    void return_matching_document_template_type(String documentType, String recordType, DocumentTemplateType expectedTemplate) {
        DocumentTemplateType result = fromDocumentTypeAndRecordType(documentType, recordType);

        Assertions.assertThat(result).isEqualTo(expectedTemplate);
    }

    private static Stream<Arguments> validTypes() {
        return Stream.of(
                Arguments.of("DEER", "INDIVIDUAL_PROSPECT", DocumentTemplateType.DEERPP),
                Arguments.of("deer", "INDIVIDUAL_PROSPECT", DocumentTemplateType.DEERPP),
                Arguments.of("Autm", "LEGAL_PROSPECT", DocumentTemplateType.AUTM),
                Arguments.of("SPEC", "ALL", DocumentTemplateType.SPEC)
        );
    }

}
