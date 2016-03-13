#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <sys/types.h>
#include <errno.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/sendfile.h>
#include <libgen.h>

#define BUFLEN 100

int main(int argc, char **argv) {
	int sock_fd, cli_fd;
	int len;
	socklen_t cli_len;
	struct sockaddr_in serv_addr;
	struct sockaddr_in cli_addr;
	char sendline[BUFLEN];
	int ret;

	if (argc != 4) {
		printf("usage: %s <IP address> <TCP port> <file path>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	// create the socket (add missing arguments)
	sock_fd =  socket(AF_INET, SOCK_STREAM, 0);
	if (!sock_fd) {
		perror("socket");
		exit(EXIT_FAILURE);
	}

	bzero(&serv_addr, sizeof(serv_addr));
	// fill in the socket family, address and port
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr(argv[1]);
	serv_addr.sin_port = htons(atoi(argv[2]));

	// set SO_REUSEADDR socket option (please explain the option's meaning)
	int so_reuseaddr = 1;
	ret = setsockopt(sock_fd,SOL_SOCKET,SO_REUSEADDR,&so_reuseaddr, sizeof so_reuseaddr);
	if (ret<0) {
		perror("setsockopt");
	}

	// bind with the use of bind procedure
	ret = bind(sock_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr));
	if (ret<0) {
		perror("bind");
	}

	// start listening with the use of listen procedure
	listen(sock_fd, 5);



    struct stat file_stat;
	int fp = open(argv[3], O_RDONLY);
	char *fileName;
	fileName = basename(argv[3]);

	printf("fiel name: %s\n", fileName);
	if (fstat(fp, &file_stat) < 0)
        {
            fprintf(stderr, "Error fstat --> %s", strerror(errno));

            exit(EXIT_FAILURE);
        }
    fprintf(stdout, "File Size: %d bytes\n", file_stat.st_size);

	while (1) {
		// accept the connection and assign descriptor to cli_fd
		cli_fd = accept(sock_fd, (struct sockaddr*)&cli_addr, &cli_len);

		int name_len = strlen(fileName);
		sendline[0] = (char) name_len;
		int i;
		for(i = 1; i < name_len + 1; i++) {
			sendline[i] = fileName[i-1];
		}

		send(cli_fd, sendline, strlen(fileName) + 1, 0);

		off_t offset = 0;
        int remain_data = file_stat.st_size;
        int sent_bytes = 0;	
		while (((sent_bytes = sendfile(cli_fd, fp, &offset, BUFSIZ	)) > 0) && (remain_data > 0))
        {
                fprintf(stdout, "1. Server sent %d bytes from file's data, offset is now : %d and remaining data = %d\n", sent_bytes, offset, remain_data);
                remain_data -= sent_bytes;
                fprintf(stdout, "2. Server sent %d bytes from file's data, offset is now : %d and remaining data = %d\n", sent_bytes, offset, remain_data);
        }

		close(cli_fd);
	}
	close(sock_fd);
	return EXIT_SUCCESS;
}

