using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;
using System.Data;

namespace Happy_Habits_App.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MedicationController : ControllerBase
    {
        private readonly IMedicationActivitiesService _medicationActivitiesService;

        public MedicationController(IMedicationActivitiesService medicationActivitiesService)
        {
            _medicationActivitiesService = medicationActivitiesService;
        }

        [HttpGet("GetMedicinesToday")]
        public async Task<IActionResult> GetMedicinesToday(string userId)
        {
            Console.WriteLine("Trying to get today's medicine!");
            List<MedicineDto> medicines = await _medicationActivitiesService.GetTodayMedicines(userId);
            return Ok(medicines);
        }

        [HttpPost("AddMedicine")]
        public async Task<IActionResult> AddMedicine([FromBody] MedicineForm form)
        {
            Console.WriteLine("Trying to add medicine");

            if (!form.IsValid)
            {
                Console.WriteLine("Invalid Attributes");
                return BadRequest("Wrong attributes of medicine form");
            }

            bool result = await _medicationActivitiesService.AddMedicine(form);

            if (!result)
            {
                Console.WriteLine("Already a medicine in the collection for the time period");
                return Conflict("Another medicine already exists for the given period");
            }
            return Ok("Medicine added succesfully");

        }

        [HttpPatch("RemoveMedicine")]
        public async Task<IActionResult> RemoveMedicine([FromBody] RemovalForm form)
        {
            Console.WriteLine("Trying to remove medicine");

            if (!form.IsValid)
            {
                Console.WriteLine("Invalid Attributes");
                return BadRequest("Wrong attributes of removal form");
            }

            await _medicationActivitiesService.RemoveMedicine(form);
            return Ok("Removed Medicine");
        }

        [HttpPost("LogMedicationActivity")]
        public async Task<IActionResult> AddMedicationActivity([FromBody] MedicationForm form)
        {
            Console.WriteLine("Trying to log medicines");

            if (!form.IsValid)
            {
                Console.WriteLine("Invalid Attributes");
                return BadRequest("Wrong attributes of medication form");
            }

            await _medicationActivitiesService.LogMedication(form);
            return Ok("Added medication activity");
        }
    }
}
