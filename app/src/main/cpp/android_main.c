#include <jni.h>
#include <android/log.h>

#define LOG_TAG "NFSHS_Android"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

// Punto de entrada nativo C para el motor del juego
int main(int argc, char *argv[]) {
    LOGI("Iniciando Need for Speed High Stakes Native Engine...");
    return 0;
}
