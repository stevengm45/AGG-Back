package com.agg.certificados.repositories.typeDocumentRepository;

import com.agg.certificados.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
}
