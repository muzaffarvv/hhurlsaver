package uz.pdp.urlsaver.controller

import org.springframework.web.bind.annotation.*
import uz.pdp.urlsaver.dto.VacancySaveDTO
import uz.pdp.urlsaver.service.VacancyService
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/vacancies")
class VacancyController(private val vacancyService: VacancyService) {

    @PostMapping
    fun saveVacancy(@RequestBody @Valid dto: VacancySaveDTO) {
        vacancyService.saveFromDto(dto)
    }

    @GetMapping
    fun getVacancies() = vacancyService.findAllVacancies()

    @PostMapping("/fetch")
    fun fetchAndSaveFromHH(@RequestParam keyword: String) {
        vacancyService.saveVacanciesFrom(keyword)
    }
}