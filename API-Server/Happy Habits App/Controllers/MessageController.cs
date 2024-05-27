using Happy_Habits_App.Forms;
using Happy_Habits_App.Hubs;
using Happy_Habits_App.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.SignalR;

namespace Happy_Habits_App.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class MessageController : ControllerBase
    {
        public IMessageService _messageService;

        public MessageController(IMessageService messageService)
        {
            _messageService = messageService;
        }

        [HttpPost("CreateFriendGroup")]
        public async Task<IActionResult> CreateFriendGroup([FromBody] FriendGroupForm group)
        {
            Console.WriteLine("Trying to create a friend group");

            bool response = await _messageService.CreateFriendGroup(group);

            if (response)
            {
                return Ok("New friend group added");
            }
            Console.WriteLine("Alreaded befriended this user");
            return Conflict("These users are already friends");
        }

        [HttpGet("RetrieveConversation")]
        public async Task<IActionResult> GetAllMessages(string groupId)
        {
            Console.WriteLine("Retrieve messages from group id: " + groupId);
            List<MessageDto> messages = await _messageService.GetMessagesOfGroupChat(groupId);
            return Ok(messages);
        }

        [HttpGet("GetFriendList")]
        public async Task<IActionResult> GetFriendGroups(string userId)
        {
            Console.WriteLine("Get all friend groups from the user with is: " + userId);
            List<FriendGroupDto> groupDtos = await _messageService.GetAllFriendGroups(userId);
            return Ok(groupDtos);
        }
    }
}