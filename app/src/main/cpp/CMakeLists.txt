cmake_minimum_required(VERSION 3.22.1)
project("nfshs")

add_definitions(-DNO_MIPS_ASM)

add_library(nfshs SHARED
    android_main.c
)

find_library(log-lib log)

target_link_libraries(nfshs
    ${log-lib}
    EGL
    GLESv2
)
