    /* Sample TCP client */

#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <inttypes.h>

#define BUFLEN 1

int64_t htonll(int64_t value) {
    int num = 42;
    if (*(char *)&num == 42) {
        int32_t high_part = htonl((int32_t)(value >> 32));
        int32_t low_part = htonl((int32_t)(value & 0xFFFFFFFFLL));
        return (((int64_t)low_part) << 32) | high_part;
    } else {
        return value;
    }
}


int main(int argc, char **argv) {
	int sock_fd;
	int len;
	struct sockaddr_in serv_addr;
	char digitToSend[8];
	char recvline[BUFLEN];

	if (argc != 3) {
		printf("usage: %s <IP address> <TCP port>\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	// create the socket (add missing arguments)
	sock_fd = socket(AF_INET, SOCK_STREAM, 0);
	if (!sock_fd) {
		perror("socket");
		exit(EXIT_FAILURE);
	}

	bzero(&serv_addr, sizeof(serv_addr));
	// fill in the socket family, address and port
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr(argv[1]);
	serv_addr.sin_port = htons(atoi(argv[2]));

	// establish the connection (SYN, SYN+ANK, ACK) with "connect" procedure
	connect(sock_fd, (struct sockaddr*)&serv_addr, sizeof(serv_addr));

	// send sendline buffer with the "send" system call and assign number of sent bytes to len
	
	printf("Which PI digit you wan to see: ");
	int num = scanf("%s", recvline);	
	int64_t x = strtoull(recvline, NULL, 0);


	int i = 0;
	int64_t tmp = x;
	while (tmp > 0) {
		tmp = tmp >> 8;
		i++;
	}

	if (i < 2) {
		char t = (char)x;
		send(sock_fd, &t, (size_t)1, 0);
	} else if (i == 2) {
		int16_t t = htons((int16_t) x);
		send(sock_fd, &t, (size_t)2, 0);
	} else if (i < 5) {
		int32_t t = htonl((int32_t) x);
		send(sock_fd, &t, (size_t)4, 0);
	} else {
		int64_t t = htonll(x);
		send(sock_fd, &t, (size_t)8, 0);
	}

	// printf("%" PRIu64 "\n", x);

	// printf("sent bytes: %d\n", len);
	// printf("sent: %s\n", sendline);

	// // receive data to recvline buffer with the "recv" system call and assign number of received bytes to len
	len = recv(sock_fd, recvline, BUFLEN, 0);
	printf("%" PRIu64 " digit of PI is %d\n", x, recvline[0]);

	return EXIT_SUCCESS;
}

