using Microsoft.AspNetCore.Mvc;
using System.Security.Cryptography.X509Certificates;
using Microsoft.AspNetCore.Authorization;
using Happy_Habits_App.Services;
using Happy_Habits_App.Forms;
using System.Text.Json;


// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Happy_Habits_App.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserService _userService;

        public UserController(IUserService userService) => _userService = userService;

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] LoginModelForm model)
        {
            Console.WriteLine("Yes Nice !!!");
            
            // Perform validation of model
            if (!model.IsValid)
            {
                return BadRequest("Not enough credentials");
            }

            // Authenticate user
            var user = await _userService.GetUserInLoginAsync(model.Password, model.Email);

            // Check if authentication successful
            if (user == null)
            {
                return Unauthorized(null); // Invalid email/password
            }
/*            string response = JsonSerializer.Serialize(user);
*/            // Return token as JSON response
            return Ok(user);
        }

        [HttpPost("SignUp")]
        public async Task<IActionResult> SignUp([FromBody] SignUpModelForm model)
        {
            // Perform validation of model
            Console.WriteLine("YAYYYYY");
            if (!model.IsValid)
            {
                return BadRequest("Not enough credentials");
            }


            // 1. Search user with same email. if found return 409-conflict with null
            var existingUser = await _userService.FindUserByEmailAsync(model.Email);
            if (existingUser != null)
            {
                return StatusCode(409);  // Returns a 409 Conflict with no content
            }

            var user = await _userService.CreateUserAsync(model);

            if (user == null)
            {
                return StatusCode(500, "Unable to create user");
            }

            // Return token as JSON response
            return Ok(user);
        }
    }
}
