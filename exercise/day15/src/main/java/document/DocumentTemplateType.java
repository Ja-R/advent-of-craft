package document;

import java.util.Arrays;
import java.util.function.Predicate;

public enum DocumentTemplateType {
    DEERPP("DEER", RecordType.INDIVIDUAL_PROSPECT),
    DEERPM("DEER", RecordType.LEGAL_PROSPECT),
    AUTP("AUTP", RecordType.INDIVIDUAL_PROSPECT),
    AUTM("AUTM", RecordType.LEGAL_PROSPECT),
    SPEC("SPEC", RecordType.ALL),
    GLPP("GLPP", RecordType.INDIVIDUAL_PROSPECT),
    GLPM("GLPM", RecordType.LEGAL_PROSPECT);

    private final String documentType;
    private final RecordType recordType;

    DocumentTemplateType(String documentType, RecordType recordType) {
        this.documentType = documentType;
        this.recordType = recordType;
    }

    public static DocumentTemplateType fromDocumentTypeAndRecordType(String documentType, String recordType) {
        return Arrays.stream(DocumentTemplateType.values())
                .filter(matchDocumentType(documentType))
                .filter(matchRecordType(recordType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Document template type or record type"));
    }

    private static Predicate<DocumentTemplateType> matchDocumentType(String documentType) {
        return templateType -> templateType.documentType.equalsIgnoreCase(documentType);
    }

    private static Predicate<DocumentTemplateType> matchRecordType(String recordType) {
        return templateType -> templateType.recordType.hasName(recordType);
    }

}
