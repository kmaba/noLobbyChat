# noLobbyChat

A simple Spigot/Paper plugin that prevents players from chatting in lobby servers while allowing staff members to communicate freely.

## Features

- Blocks chat messages from regular players
- Allows staff members with permission to chat normally
- Configurable response message with color code support
- Simple command to change the message in-game

## Commands

- `/nlc` - Shows the current no-chat message
- `/nlc <message>` - Changes the no-chat message
  - Example: `/nlc &c&lSorry! &7Chat is disabled in the lobby`

## Permissions

- `nolobbychat.admin` (default: op)
  - Allows using the /nlc command
  - Allows chatting in lobby
  
## Configuration

```yaml
# config.yml
message: "&c&lYou cannot chat in lobby, join a server to chat."
```

The plugin supports color codes using the `&` symbol.

## Installation

1. Download the plugin JAR file
2. Place it in your server's `plugins` folder
3. Restart your server
4. Configure the message using /nlc command (optional)

## Requirements

- Spigot/Paper 1.20+
- Java 8 or higher
