#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <math.h>
#include <unistd.h>
#include <sys/stat.h>

int insert(int fd, void *buf, size_t bytes, size_t offset);


int delete(int fd, size_t bytes, size_t offset);

int extract(int fd, void *buf, size_t bytes, size_t offset);