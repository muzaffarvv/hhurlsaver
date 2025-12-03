package uz.pdp.urlsaver.base

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@MappedSuperclass
abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
}
