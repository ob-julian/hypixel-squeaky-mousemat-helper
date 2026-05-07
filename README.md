# Hypixel Squeaky Mousemat Helper

A quality-of-life mod for Hypixel SkyBlock that helps you quickly identify which **Squeaky Mousemat** in your hotbar matches your current farming angle.

The mod dynamically changes the background color of each hotbar slot based on how closely your player rotation aligns with the stored angle of that Mousemat:

* 🟢 **Green** = perfectly aligned
* 🟡 **Yellow** = about 90° off
* 🔴 **Red** = facing the wrong direction

This makes it easy to instantly see which Mousemat you should activate while farming, without manually checking rotations or testing each one.

## Why?

When farming in the Garden on Hypixel SkyBlock, many players (cough I cough) use multiple **Squeaky Mousemats** with different saved angles.

Switching between them can be annoying because it’s easy to forget which Mousemat corresponds to which direction. You might find yourself activating the wrong one and start running in the wrong direction, which can be frustrating and time-consuming.

This mod solves that by giving immediate visual feedback directly in your hotbar. Instead of guessing, you can glance at the highlighted slots and instantly know which Mousemat best matches your current facing direction.


## Features

- Automatically highlights hotbar slots with player heads that have the "SQUEAKY_MOUSEMAT" ID.
- Color-coded highlighting: Green for close alignment, Red for far off.
- Parses yaw and pitch from item lore to calculate the angle difference.

## Installation

1. Ensure you have Minecraft 1.21.1 and Fabric Loader installed.
2. Download the mod JAR from the releases page (or build from source).
3. Place the JAR file in your `.minecraft/mods` folder.
4. Launch Minecraft with Fabric.

## Building from Source

1. Clone this repository.
2. Run `./gradlew build` to build the mod.
3. The JAR will be in `build/libs/`.
4. Its the one without the `sources` classifier in the filename.


## Development

For setup reference, just look at the [fabric docs](https://docs.fabricmc.net/1.21.11/develop/getting-started/setting-up).


### Running Tests
To run the unit tests, use the following command:

```bash
./gradlew test
```

### Testing the Mod in Minecraft

To run the Minecraft client with the mod for testing, use:

```bash
./gradlew runClient
```

## License

This project is released under the CC0 1.0 Universal license. See LICENSE for details.

