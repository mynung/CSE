#include "common.h"

void clear()
{
    gotoxy(25, 10);
    printf("  C C C C C     L           E E E E E     A A A A A     R R R R R   !!!");
    gotoxy(25, 11);
    printf(" C              L           E             A       A     R        R  !!!");
    gotoxy(25, 12);
    printf("C               L           E E E E E    A A A A A A    R R R R R   !!!");
    gotoxy(25, 13);
    printf(" C              L           E            A         A    R     R     ");
    gotoxy(25, 14);
    printf("  C C C C C     L L L L L   E E E E E   A           A   R       R   !!!");
    gotoxy(27, 18);
    printf("H A P P Y  H A P P Y ! ! �����մϴ�!!");
    gotoxy(27, 20);
    printf("���� �ܰ�� G O G O ~ ~");


    gotoxy(70, 16);
    printf("���������� ��n__r ��\n");
    gotoxy(70, 17);
    printf("���������� /). ��.)/)\n");
    gotoxy(70, 18);
    printf("������ ��������������\n");
    gotoxy(70, 19);
    printf("���������������������� ��\n");
    gotoxy(70, 20);
    printf("��/���������ܡ����������� ��\n");
    gotoxy(70, 21);
    printf("��!������������ ���� �塡l  \n");
    gotoxy(70, 22);
    printf("�� �� ������ ���� �� ����\n");
    gotoxy(70, 23);
    printf("���������������ʡ���������\n");
    gotoxy(0, 30);
    Sleep(2000);
    system("cls");
}

void gameover(int s)
{
    gotoxy(30, 7);
    printf("  G  G  G  G     A A A A A     M M M   M M M  E E E E E");
    gotoxy(30, 8);
    printf(" G          G    A       A     M    M M    M  E");
    gotoxy(30, 9);
    printf(" G     G  G     A A A A A A    M     M     M  E E E E");
    gotoxy(30, 10);
    printf(" G      G       A         A    M           M  E");
    gotoxy(30, 11);
    printf("  G  G  G  G   A           A   M           M  E E E E E");
    gotoxy(30, 14);
    printf("   O O O O       V             V  E E E E E   R R R R R R");
    gotoxy(30, 15);
    printf(" O         O      V           V   E           R         R");
    gotoxy(30, 16);
    printf("O           O      V         V    E           R        R");
    gotoxy(30, 17);
    printf("O           O       V       V     E E E E     R R R R R R");
    gotoxy(30, 18);
    printf("O           O        V     V      E           R         R");
    gotoxy(30, 19);
    printf(" O         O          V   V       E           R          R");
    gotoxy(30, 20);
    printf("   O O O O             V V        E E E E E   R           R");

    gotoxy(30, 22);
    printf("��Ÿ���׿� �Ф� ������ �ٽ� �����ϼ���!!");

    gotoxy(75, 22);
    printf("        /)������\n");
    gotoxy(75, 23);
    printf("��������������  \\\\\n");
    gotoxy(75, 24);
    printf(" ���������С�����\\\n");
    gotoxy(75, 25);
    printf("���������������� |\n");
    gotoxy(75, 26);
    printf("������������ �ǫ�\n");
    gotoxy(75, 27);
    printf(" U��U������U��U\n");

    gotoxy(30, 30);
    printf("  <FINAL SCORE: %d>", s);
    Sleep(3000);
    system("cls");
}

void finish(int s)
{
    gotoxy(25, 6);
    printf("�Ǳ��̰� ������ ������ ����ġ�µ� �����߽��ϴ�!!");
    Sleep(2000);
    gotoxy(25, 8);
    printf("�Ǳ����� Ȱ�� ���п� ������ ��ȭ�� ������ �� �־����ϴ�.");
    Sleep(2000);
    gotoxy(25, 10);
    printf("������� �ٽ� �ູ�� �ϻ����� ���ư��� . . .");
    Sleep(2000);
    gotoxy(25, 12);
    printf("�Ǳ��̵� ���� ����� ���� �ູ�ϰ� ��Ҵ�ϴ�.");
    Sleep(2000);
    system("cls");

    gotoxy(32, 6);
    printf(" T T T T T   H       H   E E E E E");
    Sleep(1000);
    gotoxy(32, 7);
    printf("     T       H       H   E");
    Sleep(1000);
    gotoxy(32, 8);
    printf("     T       H H H H H   E E E E E");
    Sleep(1000);
    gotoxy(32, 9);
    printf("     T       H       H   E");
    Sleep(1000);
    gotoxy(32, 10);
    printf("     T       H       H   E E E E E");
    Sleep(1000);
    gotoxy(32, 12);
    printf("  E E E E E   N      N   D D D D");
    Sleep(1000);
    gotoxy(32, 13);
    printf("  E           N  N   N   D      D");
    Sleep(1000);
    gotoxy(32, 14);
    printf("  E E E E E   N   N  N   D      D");
    Sleep(1000);
    gotoxy(32, 15);
    printf("  E           N    N N   D      D");
    Sleep(1000);
    gotoxy(32, 16);
    printf("  E E E E E   N      N   D D D D");
    Sleep(1000);
    gotoxy(32, 18);
    printf("  <FINAL SCORE: %d>", s);
    Sleep(3000);
    gotoxy(25, 24);
}