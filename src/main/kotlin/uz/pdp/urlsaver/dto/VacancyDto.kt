package uz.pdp.urlsaver.dto

import java.util.UUID

data class VacancySaveDTO(
    val guid: UUID?,
    val name: String?,
    val employerName: String?,
    val areaName: String?,
    val salaryString: String?,
    val experienceName: String?
)

data class HHResponse(
    val items: List<HHVacancy>?,
    val found: Int?,
    val pages: Int?,
    val page: Int?
)

data class HHVacancy(
    val id: String?,
    val name: String?,
    val employer: HHEmployer?,
    val area: HHArea?,
    val salary: HHSalary?,
    val experience: HHExperience?,
    val schedule: HHSchedule?,
    val publishedAt: String?,
    val alternateUrl: String?
)

data class HHEmployer(
    val id: String?,
    val name: String?,
    val url: String?,
    val alternateUrl: String?,
    val logoUrls: Map<String, String>?
)

data class HHArea(
    val id: String?,
    val name: String?,
    val url: String?
)

data class HHSalary(
    val from: Int?,
    val to: Int?,
    val currency: String?,
    val gross: Boolean?
)

data class HHExperience(
    val id: String?,
    val name: String?
)

data class HHSchedule(
    val id: String?,
    val name: String?
)