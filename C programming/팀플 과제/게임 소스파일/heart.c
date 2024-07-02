#include "common.h"

void c_hp_bar(int hp_count) { //주인공 체력 나타내기

	gotoxy(110, 16);

	for (int i = 0; i < hp_count; i++) {
		printf("\u25A0");
	}
	for (int i = 0; i < 5 - hp_count; i++)
		printf("\u25A1");
}

void b_hp_bar(int hp_count) {  //보스 체력 나타내기

	gotoxy(6, 16);

	for (int i = 0; i < hp_count; i++) {
		printf("\u25A0");
	}
	for (int i = 0; i < 5 - hp_count; i++)
		printf("\u25A1");
}