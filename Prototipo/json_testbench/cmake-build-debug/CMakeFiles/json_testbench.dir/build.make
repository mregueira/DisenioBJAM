# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.20

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2021.2.1\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2021.2.1\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/json_testbench.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/json_testbench.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/json_testbench.dir/flags.make

CMakeFiles/json_testbench.dir/main.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/main.c.obj: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/json_testbench.dir/main.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\main.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\main.c

CMakeFiles/json_testbench.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/main.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\main.c > CMakeFiles\json_testbench.dir\main.c.i

CMakeFiles/json_testbench.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/main.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\main.c -o CMakeFiles\json_testbench.dir\main.c.s

CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.obj: ../Comeca/Messages/Send/Src/messageLayer.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building C object CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\Messages\Send\Src\messageLayer.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Messages\Send\Src\messageLayer.c

CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Messages\Send\Src\messageLayer.c > CMakeFiles\json_testbench.dir\Comeca\Messages\Send\Src\messageLayer.c.i

CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Messages\Send\Src\messageLayer.c -o CMakeFiles\json_testbench.dir\Comeca\Messages\Send\Src\messageLayer.c.s

CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.obj: ../Comeca/Messages/Receive/Src/jsonGetter.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\Messages\Receive\Src\jsonGetter.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Messages\Receive\Src\jsonGetter.c

CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Messages\Receive\Src\jsonGetter.c > CMakeFiles\json_testbench.dir\Comeca\Messages\Receive\Src\jsonGetter.c.i

CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Messages\Receive\Src\jsonGetter.c -o CMakeFiles\json_testbench.dir\Comeca\Messages\Receive\Src\jsonGetter.c.s

CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.obj: ../Comeca/EthernetEvent/Src/ethernet.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building C object CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\EthernetEvent\Src\ethernet.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\EthernetEvent\Src\ethernet.c

CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\EthernetEvent\Src\ethernet.c > CMakeFiles\json_testbench.dir\Comeca\EthernetEvent\Src\ethernet.c.i

CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\EthernetEvent\Src\ethernet.c -o CMakeFiles\json_testbench.dir\Comeca\EthernetEvent\Src\ethernet.c.s

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.obj: ../Comeca/Managers/Src/caliperManager.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building C object CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\caliperManager.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\caliperManager.c

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\caliperManager.c > CMakeFiles\json_testbench.dir\Comeca\Managers\Src\caliperManager.c.i

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\caliperManager.c -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\caliperManager.c.s

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.obj: ../Comeca/Managers/Src/pieceCountManager.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building C object CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\pieceCountManager.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\pieceCountManager.c

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\pieceCountManager.c > CMakeFiles\json_testbench.dir\Comeca\Managers\Src\pieceCountManager.c.i

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\pieceCountManager.c -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\pieceCountManager.c.s

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.obj: ../Comeca/Managers/Src/digitalOutManager.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Building C object CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\digitalOutManager.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\digitalOutManager.c

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\digitalOutManager.c > CMakeFiles\json_testbench.dir\Comeca\Managers\Src\digitalOutManager.c.i

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\digitalOutManager.c -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\digitalOutManager.c.s

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.obj: ../Comeca/Managers/Src/analogInManager.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_8) "Building C object CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\analogInManager.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\analogInManager.c

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\analogInManager.c > CMakeFiles\json_testbench.dir\Comeca\Managers\Src\analogInManager.c.i

CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\Managers\Src\analogInManager.c -o CMakeFiles\json_testbench.dir\Comeca\Managers\Src\analogInManager.c.s

CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.obj: ../Comeca/DigimaticProtocol/Src/digimatic.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_9) "Building C object CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\Comeca\DigimaticProtocol\Src\digimatic.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\DigimaticProtocol\Src\digimatic.c

CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\DigimaticProtocol\Src\digimatic.c > CMakeFiles\json_testbench.dir\Comeca\DigimaticProtocol\Src\digimatic.c.i

CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\Comeca\DigimaticProtocol\Src\digimatic.c -o CMakeFiles\json_testbench.dir\Comeca\DigimaticProtocol\Src\digimatic.c.s

CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.obj: ../testBenchUtils/Src/testBenchVars.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_10) "Building C object CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\testBenchUtils\Src\testBenchVars.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\testBenchUtils\Src\testBenchVars.c

CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\testBenchUtils\Src\testBenchVars.c > CMakeFiles\json_testbench.dir\testBenchUtils\Src\testBenchVars.c.i

CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\testBenchUtils\Src\testBenchVars.c -o CMakeFiles\json_testbench.dir\testBenchUtils\Src\testBenchVars.c.s

CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.obj: CMakeFiles/json_testbench.dir/flags.make
CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.obj: ../ExternalLibs/JSONParser/Src/cJSON.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_11) "Building C object CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.obj"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\json_testbench.dir\ExternalLibs\JSONParser\Src\cJSON.c.obj -c C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\ExternalLibs\JSONParser\Src\cJSON.c

CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.i"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\ExternalLibs\JSONParser\Src\cJSON.c > CMakeFiles\json_testbench.dir\ExternalLibs\JSONParser\Src\cJSON.c.i

CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.s"
	C:\MinGW\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\ExternalLibs\JSONParser\Src\cJSON.c -o CMakeFiles\json_testbench.dir\ExternalLibs\JSONParser\Src\cJSON.c.s

# Object files for target json_testbench
json_testbench_OBJECTS = \
"CMakeFiles/json_testbench.dir/main.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.obj" \
"CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.obj" \
"CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.obj" \
"CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.obj"

# External object files for target json_testbench
json_testbench_EXTERNAL_OBJECTS =

json_testbench.exe: CMakeFiles/json_testbench.dir/main.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/Messages/Send/Src/messageLayer.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/Messages/Receive/Src/jsonGetter.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/EthernetEvent/Src/ethernet.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/Managers/Src/caliperManager.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/Managers/Src/pieceCountManager.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/Managers/Src/digitalOutManager.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/Managers/Src/analogInManager.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/Comeca/DigimaticProtocol/Src/digimatic.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/testBenchUtils/Src/testBenchVars.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/ExternalLibs/JSONParser/Src/cJSON.c.obj
json_testbench.exe: CMakeFiles/json_testbench.dir/build.make
json_testbench.exe: CMakeFiles/json_testbench.dir/linklibs.rsp
json_testbench.exe: CMakeFiles/json_testbench.dir/objects1.rsp
json_testbench.exe: CMakeFiles/json_testbench.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_12) "Linking C executable json_testbench.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\json_testbench.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/json_testbench.dir/build: json_testbench.exe
.PHONY : CMakeFiles/json_testbench.dir/build

CMakeFiles/json_testbench.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\json_testbench.dir\cmake_clean.cmake
.PHONY : CMakeFiles/json_testbench.dir/clean

CMakeFiles/json_testbench.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug C:\Github_Repos\DisenioBJAM\Prototipo\json_testbench\cmake-build-debug\CMakeFiles\json_testbench.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/json_testbench.dir/depend

