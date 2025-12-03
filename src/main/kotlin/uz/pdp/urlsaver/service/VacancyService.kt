package uz.pdp.urlsaver.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.RestClientException
import uz.pdp.urlsaver.dto.*
import uz.pdp.urlsaver.repo.VacancyRepo
import uz.pdp.urlsaver.mapper.VacancyMapper

@Service
class VacancyService(private val vacancyRepo: VacancyRepo) {
    private val log = LoggerFactory.getLogger(VacancyService::class.java)
    private val restTemplate = RestTemplate()

    @Transactional
    fun saveFromDto(dto: VacancySaveDTO) {
        vacancyRepo.save(VacancyMapper.toEntity(dto))
    }

    fun findAllVacancies() = vacancyRepo.findAll()

    @Transactional
    fun saveVacanciesFrom(keyword: String) {

        try {
            val url = "https://api.hh.ru/vacancies?text=$keyword&per_page=50"

            val response = restTemplate.getForObject(url, HHResponse::class.java)

            if (response?.items.isNullOrEmpty()) {
                log.info("No vacancies found for keyword: {}", keyword)
                return
            }

            log.info("Found {} vacancies for keyword: {}", response?.items?.size, keyword)

            response?.items?.forEach { vacancy ->
                try {
                    val salaryString = formatSalary(vacancy.salary)
                    val pictureUrl = vacancy.employer?.logoUrls?.get("240")

                    val vacancyEntity = VacancyMapper.toEntity(
                        vacancyDto = vacancy,
                        salaryString = salaryString,
                        pictureUrl = pictureUrl
                    )

                    vacancyRepo.save(vacancyEntity)

                } catch (e: Exception) {
                    log.error("Error saving vacancy {}: {}", vacancy.id, e.message, e)
                }
            }

            log.info("Successfully saved {} vacancies", response?.items?.size)

        } catch (e: RestClientException) {
            log.error("Error calling HH API: {}", e.message, e)
            throw RuntimeException("Failed to fetch data from HH.ru API", e)
        } catch (e: Exception) {
            log.error("Unexpected error: {}", e.message, e)
            throw RuntimeException("Failed to process HH.ru data", e)
        }
    }

    private fun formatSalary(salary: HHSalary?): String? {
        if (salary == null) return null

        return buildString {
            if (salary.from != null && salary.to != null) {
                append("${salary.from} - ${salary.to}")
            } else if (salary.from != null) {
                append("from ${salary.from}")
            } else if (salary.to != null) {
                append("to ${salary.to}")
            }

            if (salary.currency != null) {
                append(" ${salary.currency}")
            }
        }.ifBlank { null }
    }
}