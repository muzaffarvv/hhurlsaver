package uz.pdp.urlsaver.model

import jakarta.persistence.*
import uz.pdp.urlsaver.base.BaseModel

@Entity
@Table(name = "vacancies")
class Vacancy(
    var name: String? = null,
    var employerName: String? = null,
    var areaName: String? = null,
    var salaryString: String? = null,
    var alternateUrl: String? = null,
    var publishedAt: String? = null,
    var experienceName: String? = null,
    var pictureUrl: String? = null,

    var greeting: String? = null,
    var isActive: Boolean = true

) : BaseModel()
