#include "common.h"

void bomb(int bomb_set)
{

	gotoxy(103, 18);
	for (int i = 0; i < bomb_set * 2; i++) {
		printf("¡Ü~'\b\b\b\b");
		Sleep(70);
		printf("    ");

		if (i < bomb_set)
			gotoxy(103 - i * 3, 18 - i);
		else
			gotoxy(103 - i * 3, 18 - bomb_set * 2 + i);

	}
}