package uz.pdp.urlsaver.mapper

import uz.pdp.urlsaver.dto.HHVacancy
import uz.pdp.urlsaver.dto.VacancySaveDTO
import uz.pdp.urlsaver.model.Vacancy

object VacancyMapper {

    fun toEntity(
        vacancyDto: HHVacancy,
        salaryString: String?,
        pictureUrl: String?
    ): Vacancy {

        return Vacancy(
            name = vacancyDto.name,
            employerName = vacancyDto.employer?.name,
            areaName = vacancyDto.area?.name,
            salaryString = salaryString,
            alternateUrl = vacancyDto.alternateUrl,
            publishedAt = vacancyDto.publishedAt,
            experienceName = vacancyDto.experience?.name,
            pictureUrl = pictureUrl,
            greeting = "Vacancy: ${vacancyDto.name} at ${vacancyDto.employer?.name ?: "Company"}",
            isActive = true
        )
    }

    fun toEntity(saveDto: VacancySaveDTO): Vacancy {
        return Vacancy(
            name = saveDto.name,
            employerName = saveDto.employerName,
            areaName = saveDto.areaName,
            salaryString = saveDto.salaryString
        )
    }
}