package uz.pdp.urlsaver.model

import jakarta.persistence.*
import uz.pdp.urlsaver.base.BaseModel

@Entity
@Table(name = "friends")
class Friend(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy_id")
    var vacancy: Vacancy? = null,

    var name: String? = null,
) : BaseModel()