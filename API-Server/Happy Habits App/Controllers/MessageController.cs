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
        public async Task<IActionResult> CreateFriendGroup()
        {
            return Ok();
        }

        [HttpGet("RetrieveConversation")]
        public async Task<IActionResult> GetAllMessages(string groupId)
        {
            Console.WriteLine("Retrive messages from group id: " + groupId);
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
