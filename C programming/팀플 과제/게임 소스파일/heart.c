#include "common.h"

void c_hp_bar(int hp_count) { //���ΰ� ü�� ��Ÿ����

	gotoxy(110, 16);

	for (int i = 0; i < hp_count; i++) {
		printf("\u25A0");
	}
	for (int i = 0; i < 5 - hp_count; i++)
		printf("\u25A1");
}

void b_hp_bar(int hp_count) {  //���� ü�� ��Ÿ����

	gotoxy(6, 16);

	for (int i = 0; i < hp_count; i++) {
		printf("\u25A0");
	}
	for (int i = 0; i < 5 - hp_count; i++)
		printf("\u25A1");
}