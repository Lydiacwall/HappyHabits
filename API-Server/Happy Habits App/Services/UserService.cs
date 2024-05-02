using Happy_Habits_App.Configurations;
using Happy_Habits_App.Forms;
using Happy_Habits_App.Model;
using Happy_Habits_App.Repositories;

using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;

namespace Happy_Habits_App.Services
{
    public class UserService : IUserService
    {
        private readonly IUserRepository _usersRepository;
        public UserService(IUserRepository userRepository)
        {
            _usersRepository = userRepository;
        }

        public async Task<User> GetUserInLoginAsync(string? password, string? email)
        {
            User user = await _usersRepository.GetUserByPasswordAndEmailAsync(password, email);

            if (user != null)
            {
                // Get today's date without the time component
                var today = DateTime.UtcNow.Date;
                // Check if the last login was yesterday
                var wasLastLoginYesterday = user.LastLogInDate.Date == today.AddDays(-1);
                
                if (user.LastLogInDate != today)
                {
                    user.LastLogInDate = today;

                    if (wasLastLoginYesterday == true)
                    {
                        user.Streak++;
                    }
                    else
                    {
                        user.Streak = 0;
                    }
                    await _usersRepository.UpdateUserAsync(user);
                }
                
            }
            return user;
        }

        public async Task<User?> CreateUserAsync(SignUpModelForm model)
        {
            User user = new User();
            user.FirstName = model.FirstName;
            user.LastName = model.LastName;
            user.Password = model.Password;
            user.Email = model.Email;
            user.Birthdate = model.Birthdate;
            user.Speciality = model.Speciality;
            user.Type = model.Type;

            await _usersRepository.CreateUserAsync(user);

            return await _usersRepository.GetUserByEmail(model.Email);
        }

        public async Task<User?> FindUserByEmailAsync(string? email)
        {
            //Search if there is another user with the same email
            return await _usersRepository.GetUserByEmail(email);
        }
    }
}
