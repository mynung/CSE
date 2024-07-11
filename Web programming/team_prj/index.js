// 게임 설정 관련 변수

var stageNum; //현재 스테이지 변수
var sphereNum = 1; //구체 번호 변수
var isMouseDown = false; // 마우스가 눌린 상태를 추적
var isEffectSoundOn = true; // 효과음 상태를 추적
let isFinish = false; //게임 끝났을 때
let overSound; //게임 오버 사운드

//캔버스 관련 변수
var canvas;
var context;
var canvasWidth = 1200;
var canvasHeight = 700;

//공 관련 변수
var ballImg = new Image(); //공 이미지
var ball = new Object(); //볼 객체
var x;
var y;
var dx = 3;
var dy = -3;
var ballRadius = 15;

//캐릭터 관련 변수
var charImg = new Image(); //케릭터 이미지(아리)
var minion1Img = new Image(); //원거리 미니언
var minion2Img = new Image(); //근거리 미니언
var minion3Img = new Image(); //대포 미니언
var messengerImg = new Image(); //전령
var larvaImg = new Image(); //유충
var baronImg = new Image(); //바론
const charSize = 50;

//패드관련 변수
var paddleHeight = 20;
var paddleWidth = 120;
var paddleX;

//벽돌 관련 변수

const brickRowCount = 6;
const brickColumnCount = 10;
const brickSize = 50;
const brickPadding = 20;
const brickOffsetTop = 30;
var brickOffsetLeft = 300;

let brick = [];

//스킬관련 변수
var qEffectUsed = false;
var wEffectUsed = false;
var eEffectUsed = false;
let EE = false;
let stun = false;

//플레이어 생명
var life = 3;

//타이머 변수(진짜 타이머가 아니라 캔버스 10밀리세컨드씩 그리게 해주는거)
var timer;

//점수 관련 변수
var score;
let refernceTime; //게임 점수관련 기준 시간
let spendTime; //게임 시작하고 지난시간

function playClickSound() {
  if (isEffectSoundOn) {
    document.getElementById("click-sound").play();
  }
}

function ballSound() {
  if (isEffectSoundOn) {
    document.getElementById("ball-sound").play();
  }
}

function showSettings() {
  playClickSound();
  clearScreen();
  $("#screen-setting").show();
}

function selectSphere(num) {
  $(".sphere").removeClass("selected");
  $("#sphere" + num).addClass("selected");
  sphereNum = num;
}

function handleToggleSwitch() {
  $("#effect-sound").change(function () {
    isEffectSoundOn = $(this).is(":checked");
    if (!isEffectSoundOn) {
      document.getElementById("click-sound").pause();
    }
  });

  $("#background-music").change(function () {
    if ($(this).is(":checked")) {
      // 배경음악 켜기
      $("#bg-music")[0].play();
    } else {
      // 배경음악 끄기
      $("#bg-music")[0].pause();
    }
  });
}

window.onload = function () {
  selectSphere(1);
  handleToggleSwitch();
  canvas = document.getElementById("game-play");
  context = canvas.getContext("2d");
  x = canvas.width / 2;
  y = canvas.height / 2;
  paddleX = (canvas.width - paddleWidth) / 2;
  overSound = document.getElementById("gameOver-sound");
  $("#game_start").click(function () {
    playClickSound();
    clearScreen();
    $("#screen-difficulty").show();
  });

  $("#setting").click(function () {
    playClickSound();
    clearScreen();
    $("#screen-setting").show();
  });

  $("#how_to_play").click(function () {
    playClickSound();
    clearScreen();
    $("#screen-howPlay").show();
  });

  // 성욱
  $(".confirm-button").on("mousedown", function () {
    $(this).css("background-image", "url(src/img/confirm_press.png)");
    playClickSound();
  });

  $(".confirm-button").on("mouseup", function (event) {
    $(this).css("background-image", "url(src/img/confirm.png)");

    var buttonWidth = $(this).outerWidth();
    var clickX = event.offsetX;

    if (clickX <= buttonWidth / 6) {
      clearScreen(); //메인 화면으로 돌아감
      resetStageButtons(); // 스테이지 버튼 초기화
      $("#screen").show();
    } else {
      $("#main-screen").hide();
      loadingPage(); //게임 화면 들어감
    }
  });

  $(".confirm-button").on("mouseleave", function () {
    if ($(this).is(":active")) {
      $(this).css("background-image", "url(src/img/confirm.png)");
    }
  });

  $(".stage-button").on("mousedown", function () {
    isMouseDown = true; // 마우스가 눌린 상태를 기록
    $(".stage-button")
      .removeClass("active clicked")
      .css("background-image", "url(src/img/button_unclick.png)");

    $(this)
      .addClass("active")
      .css("background-image", "url(src/img/button_press.png)");
    playClickSound();
    stageNum = parseInt($(this).attr("data-stage"));
  });

  $(".stage-button").on("mouseup", function () {
    isMouseDown = false; // 마우스가 눌린 상태를 해제
    if ($(this).hasClass("active")) {
      $(this)
        .removeClass("active")
        .addClass("clicked")
        .css("background-image", "url(src/img/button_click.png)");
    }
  });

  $(".stage-button").on("mouseleave", function () {
    if (isMouseDown) {
      // 마우스가 눌린 상태에서 벗어났을 때만 실행
      $(this).removeClass("active");
      $(this).css("background-image", "url(src/img/button_unclick.png)");
    }
  });

  // 페이지 전환 시 마우스 상태 초기화
  $(document).on("mouseup", function () {
    isMouseDown = false;
  });
  //마우스 동작 감지(패드 움직이게 할려고)
  $(document).on("mousemove", function (e) {
    const relativeX = e.pageX - $(canvas).offset().left;
    if (
      relativeX > paddleWidth / 4 &&
      relativeX < canvas.width - paddleWidth / 4
    ) {
      if (!stun) {
        paddleX = relativeX - paddleWidth / 2;
      }
    }
  });

  // 초기 설정
  canvas.setAttribute("width", canvasWidth);
  canvas.setAttribute("height", canvasHeight);
  charImg.src = "src/img/아리.png";
  minion1Img.src = "src/img/minion1.png";
  minion2Img.src = "src/img/minion2.png";
  minion3Img.src = "src/img/minion3.png";
  messengerImg.src = "src/img/전령.png";
  larvaImg.src = "src/img/유충.png";
  baronImg.src = "src/img/바론.png";
};

function resetStageButtons() {
  $(".stage-button")
    .removeClass("active clicked")
    .css("background-image", "url(src/img/button_unclick.png)");
}

function loadingPage() {
  var skip = false;
  function skipStory(event) {
    if (event.keyCode == 83) {
      //스킵 기능
      skip = true;
      $(document).off("keydown", skipStory);
      mainGame();
      return;
    }
  }

  $(document).on("keydown", skipStory);
  var text = [
    "아리에게는 포로라는 애완 동물이 있었다",
    "하지만 포로는 먹이를 찾으러 갔다가 바론에게 납치당했다",
    "과연 아리는 바론에게서 포로를 구출할 수 있을 것인가?",
    "포로 구출 작전!",
  ];
  var typingSpeed = 50;

  $("#loading").show();

  function textShow(text, index) {
    if (index < text.length) {
      $("#typed-text").append(text[index]);
      setTimeout(function () {
        textShow(text, index + 1);
      }, typingSpeed);
    } else {
      setTimeout(function () {
        $("#typed-text").fadeOut("slow");
        $("#typed-text").empty();
      }, 2000);
    }
  }

  function showTexts(i) {
    if (i < text.length) {
      $("#typed-text").show();
      if (i == text.length - 1) {
        $("#typed-text").css("font-size", "100px");
        typingSpeed = 100;
      }
      textShow(text[i], 0);
      setTimeout(function () {
        showTexts(i + 1);
      }, text[i].length * typingSpeed + 5000);
    } else {
      $(document).off("keydown", skipStory);
      if (skip == false) {
        mainGame();
      }
    }
  }

  showTexts(0);
}

// 메인 게임화면
function mainGame() {
  if (stageNum != 4) {
    init();
    showGameText();
    setTimeout(function () {
      switch (stageNum) {
        case 1:
          stage1();
          break;
        case 2:
          stage2();
          break;
        case 3:
          stage3();
          break;
      }
    }, 7000);
  } else {
    Ending();
  }
}

function showGameText() {
  $("#game-text").show();
  setTimeout(function () {
    $("#game-text").html(`stage ${stageNum}`);
  }, 500);
  setTimeout(function () {
    $("#game-text").html(`3`);
  }, 3000);
  setTimeout(function () {
    $("#game-text").html(`2`);
  }, 4000);
  setTimeout(function () {
    $("#game-text").html(`1`);
  }, 5000);
  setTimeout(function () {
    $("#game-text").html(`GAME START`);
  }, 6000);
  setTimeout(function () {
    $("#game-text").fadeOut("slow");
  }, 6500);
}

function stage1() {
  x = canvasWidth / 2;
  y = (canvasHeight / 4) * 3;
  brickOffsetLeft =
    (canvasWidth - (brickSize + brickPadding) * brickColumnCount) / 2; //벽돌 가운데 배치
  $("canvas").show();
  $("#menu-bar").show();
  refernceTime = 150 * 100; //stage 1 기준 시간 150초
  //생성 방향 랜덤
  var rand = Math.floor(Math.random() * 2);
  if (rand) {
    dx = -dx;
  } else {
    dy = -dy;
  }

  for (var c = 0; c < brickColumnCount; c++) {
    brick[c] = [];
    for (var r = 0; r < brickRowCount; r++) {
      brick[c][r] = { x: 0, y: 0, brickType: 0, brickLife: 0, size: brickSize };
      if (r == 0 && c > 0 && c < 9) {
        brick[c][r].brickType = 1; //원거리 미니언 타입 = 1
        brick[c][r].brickLife = 1;
      }
      if (r == 1 && c > 1 && c < 5) {
        brick[c][r].brickType = 3; //대포 미니언 타입 = 3
        brick[c][r].brickLife = 5;
      }
      if (r == 3 && c > 1 && c < 8) {
        brick[c][r].brickType = 2; //근거리 미니언 타입  = 2
        brick[c][r].brickLife = 1;
      }
    }
  }

  timer = setInterval(moving, 10);
}
function stage2() {
  brickOffsetLeft =
    (canvasWidth - (brickSize + brickPadding) * brickColumnCount) / 2; //벽돌 가운데 배치
  const messengerC = 3;
  const messengerR = 0;
  x = canvasWidth / 2;
  y = (canvasHeight / 4) * 3;
  let reviveTimer;
  $("canvas").show();
  $("#menu-bar").show();
  refernceTime = 150 * 100; //stage 1 기준 시간 150초
  var rand = Math.floor(Math.random() * 2);
  if (rand) {
    dx = -dx;
  }

  for (var c = 0; c < brickColumnCount; c++) {
    brick[c] = [];
    for (var r = 0; r < brickRowCount; r++) {
      brick[c][r] = {
        x: 0,
        y: 0,
        brickType: 0,
        brickLife: 0,
        size: brickSize,
      };
      if (r == messengerR && c == messengerC) {
        brick[c][r].brickType = 4; //전령 타입 = 4
        brick[c][r].brickLife = 30;
      }

      if (r == 3 && c == 8) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }

      if (r == 4) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }
      if (r == 5 && c == 9) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }
      if (r == 5 && c == 0) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }
      if (r == 2 && c == 0) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }
      if (r == 2 && c == 6) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }
      if (r == 5 && c == 2) {
        brick[c][r].brickType = 5; //유충 타입  = 5
        brick[c][r].brickLife = 1;
      }
    }
  }

  timer = setInterval(moving, 10);
  reviveTimer = setInterval(revive, 5000);

  //유충 부활
  function revive() {
    if (isFinish) {
      clearInterval(reviveTimer);
    }
    const messengerBrick = brick[messengerC][messengerR];
    for (let c = 0; c < brickColumnCount; c++) {
      for (let r = 0; r < brickRowCount; r++) {
        const b = brick[c][r];
        if (
          b.brickType == 5 &&
          b.brickLife <= 0 &&
          messengerBrick.brickLife > 0
        ) {
          b.brickLife = 1;
        }
      }
    }
  }
}

function stage3() {
  let passive;
  x = canvasWidth / 2;
  y = (canvasHeight / 4) * 3;
  brickOffsetLeft =
    (canvasWidth - (brickSize + brickPadding) * brickColumnCount) / 2; //벽돌 가운데 배치

  $("canvas").show();
  $("#menu-bar").show();
  refernceTime = 150 * 100; //stage 1 기준 시간 150초
  var rand = Math.floor(Math.random() * 2);
  if (rand) {
    dx = -dx;
  }
  for (var c = 0; c < brickColumnCount; c++) {
    brick[c] = [];
    for (var r = 0; r < brickRowCount; r++) {
      brick[c][r] = {
        x: 0,
        y: 0,
        brickType: 0,
        brickLife: 0,
        size: brickSize,
      };
      const b = brick[c][r];
      if (r == 0 && c == 2) {
        b.brickType = 6; //바론 타임임
        b.brickLife = 100;
      }
      if (r == 2) {
        b.brickType = 3;
        b.brickLife = 5;
      }

      if (r == 4) {
        b.brickType = 1; //원거리 미니언
        b.brickLife = 1;
      }
      if (r == 4) {
        b.brickType = 5; //유충
        b.brickLife = 1;
      }
      if (c == 0) {
        b.brickType = 5; //유충
        b.brickLife = 1;
      }
      //스테이지 1, 스테이지 2 참고해서 if문으로 뭐나오게 할지 설정
    }
  }
  passive = setInterval(baronPassive, 10000);
  function baronPassive() {
    const baronB = brick[2][0];
    if (baronB.brickLife > 0) {
      let randPassive = Math.floor(Math.random() * 3);
      if (randPassive == 0) {
        //유충 부활
        for (let c = 0; c < brickColumnCount; c++) {
          for (let r = 0; r < brickRowCount; r++) {
            const b = brick[c][r];
            if (b.brickType == 5 && b.brickLife <= 0) {
              b.brickLife = 1;
            }
          }
        }
      } else if (randPassive == 1) {
        //바론 체력회복
        baronB.brickLife += 5;
      } else {
        //사용자 스턴
        stun = true;
        setTimeout(function () {
          stun = false;
        }, 1500);
      }
    } else {
      clearInterval(passive);
    }
  }
  timer = setInterval(moving, 10);
}

function moving() {
  spendTime++; //시간 지나게 하는거
  context.clearRect(0, 0, canvas.width, canvas.height);
  drawPaddle();
  brickDetection();
  if (EE) {
    context.beginPath();
    context.arc(x, y, ballRadius, 0, Math.PI * 2);
    context.fillStyle = "red";
    context.fill();
    context.closePath();
  } else {
    context.drawImage(ballImg, x, y, ballRadius * 2, ballRadius * 2);
  }
  drawBrick();
  gameWin();

  x += dx;
  y += dy;

  //벽 충돌
  if (x + dx > canvas.width - ballRadius || x + dx < ballRadius / 6) {
    ballSound();
    dx = -dx;
  }
  if (y + dy < ballRadius / 6) {
    ballSound();
    dy = -dy;
  } else if (y + dy > canvas.height - ballRadius - paddleHeight) {
    if (x > paddleX && x < paddleX + paddleWidth) {
      ballSound();
      dy = -dy;
    } else {
      if (--life > 0) {
        $("#heart").attr("src", `src/img/하트${life}.png`);
      } else {
        clearInterval(timer);
        isFinish = true;
        gameOver();
      }
      x = canvas.width / 2;
      y = canvas.height / 2;
      rand = Math.floor(Math.random() * 2);
      if (rand) {
        dx = -dx;
      } else {
        dy = -dy;
      }
    }
  }
}

function gameOver() {
  $("#game-main").hide();
  $("#game-over-screen").show();
  var bgMusic = document.getElementById("bg-music");
  bgMusic.pause();
  bgMusic.currentTime = 0;
  if (isEffectSoundOn) {
    overSound.play();
  }
}

function restartGame() {
  $("#game-over-screen").hide();
  document.location.reload();
  overSound.pause();
}

//게임 승리했을 때 감지
function gameWin() {
  let detectWin = true; //벽돌이 하나라도 있다면 false
  for (let c = 0; c < brickColumnCount; c++) {
    for (let r = 0; r < brickRowCount; r++) {
      const b = brick[c][r];
      if (b.brickLife > 0) {
        detectWin = false;
      }
    }
  }
  //게임 승리했을 때

  if (detectWin == true) {
    isFinish = true;
    clearInterval(timer);
    $("#game-main").hide();
    score = (refernceTime - spendTime) * 10;
    //이 아래에 if문으로 점수에 따른 티어할당하면됨 (단 tier변수에 대문자 영어로된 티어 변수로 매기기!!)
    //ex) tier = GOLD;
    if (score >= 80000) {
      $("#tier-image").attr("src", "src/img/ChallengerTier.png");
      $("#tier-text").text("Challenger");
    } else if (score >= 60000) {
      $("#tier-image").attr("src", "src/img/EmeraldTier.png");
      $("#tier-text").text("Emerald");
    } else if (score >= 50000) {
      $("#tier-image").attr("src", "src/img/GoldTier.png");
      $("#tier-text").text("Gold");
    } else {
      $("#tier-image").attr("src", "src/img/BronzeTier.png");
      $("#tier-text").text("Bronze");
    }
    $("#final-score").html(`${score}`);
    $("#gamewin").show();
  }
}
function Ending() {
  $("#loading").hide();
  $("#game-main").hide();
  $("canvas").hide();
  $("#menu-bar").hide();
  $("#game-over-screen").hide();
  $("#gamewin").hide();
  $("#end").show();
}
function nextStage() {
  if (stageNum <= 3) {
    stageNum++;
    mainGame();
  }
}

function drawPaddle() {
  context.beginPath();
  context.rect(
    paddleX,
    canvas.height - paddleHeight,
    paddleWidth,
    paddleHeight
  );
  context.fillStyle = "red";
  context.fill();
  context.closePath();
  context.drawImage(
    charImg,
    paddleX + paddleWidth / 2,
    canvas.height - paddleHeight - charSize,
    charSize,
    charSize
  );
}

//벽돌 생성
function drawBrick() {
  for (var c = 0; c < brickColumnCount; c++) {
    for (var r = 0; r < brickRowCount; r++) {
      if (brick[c][r].brickLife > 0) {
        const b = brick[c][r];
        switch (b.brickType) {
          case 3: //대포 크기만 다르게
            b.size = brickSize + 30;
            break;
          case 4: //전령 크기만 다르게
            b.size = brickSize + 40;
            break;
          case 6:
            b.size = brickSize + 80;
            break;
          default:
            b.size = brickSize;
            break;
        }
        b.x = c * (brickPadding + b.size) + brickOffsetLeft;
        b.y = r * (brickPadding + b.size) + brickOffsetTop;

        switch (b.brickType) {
          case 1:
            context.drawImage(minion1Img, b.x, b.y, b.size, b.size);
            break;
          case 2:
            context.drawImage(minion2Img, b.x, b.y, b.size, b.size);
            break;
          case 3:
            context.drawImage(minion3Img, b.x, b.y, b.size, b.size);
            break;
          case 4:
            context.drawImage(messengerImg, b.x, b.y, b.size, b.size);
            break;
          case 5:
            context.drawImage(larvaImg, b.x, b.y, b.size, b.size);
            break;
          case 6:
            context.drawImage(baronImg, b.x, b.y, b.size, b.size);
            break;
        }
      }
    }
  }
}

//벽돌 충돌 감지
function brickDetection() {
  for (let c = 0; c < brickColumnCount; c++) {
    for (let r = 0; r < brickRowCount; r++) {
      const b = brick[c][r];
      if (b.brickLife > 0) {
        if (
          x + dx + ballRadius >= b.x &&
          x + dx <= b.x + b.size &&
          y + dy + ballRadius >= b.y &&
          y + dy <= b.y + b.size
        ) {
          ballSound();
          dy = -dy;
          if (EE) {
            b.brickLife -= 5; //강화 구체를 썼을 때 -1이 아닌 -5로 벽돌 데미지
            EE = false;
          } else {
            b.brickLife--;
          }
        }
      }
    }
  }
}
// 초기화 담당 함수
function init() {
  $("#loading").hide();
  $("#game-main").show();
  $("canvas").hide();
  $("#menu-bar").hide();
  $("#game-over-screen").hide();
  $("#gamewin").hide();
  $("#end").hide();
  spendTime = 0;
  score = 0;
  life = 3;
  isFinish = false;
  qEffectUsed = false;
  wEffectUsed = false;
  eEffectUsed = false;
  stun = false;
  $("#heart").attr("src", `src/img/하트${life}.png`);
  EE = false;
  detectWin = false;
  $("#skills img:nth-child(1)").css("filter", "none");
  $("#skills img:nth-child(2)").css("filter", "none");
  $("#skills img:nth-child(3)").css("filter", "none");

  context.clearRect(0, 0, canvasWidth, canvasHeight);
  ballImg.src = `src/img/구체${sphereNum}.png`;
}

//게임 초기화면 없애기
function clearScreen() {
  $("#screen").hide();
  $("#screen-difficulty").hide();
  $("#screen-setting").hide();
  $("#screen-howPlay").hide();
}

// 스킬 코드

// var originalBallRadius = ballRadius; // 원래 공 크기 저장
// var originalDx = dx; // 원래 공 속도 저장
// var originalDy = dy;
// var originalPaddleWidth = paddleWidth; // 원래 패들 너비 저장
// Q 키 효과: 공 크기 증가
function applyQEffect() {
  ballRadius *= 2; // 공 크기 2배 증가
  setTimeout(() => {
    ballRadius = ballRadius / 2; // 5초 후 원래 크기로 복원
  }, 5000); // 5초 지속
}

// W 키 효과: 패들 크기 증가
function applyWEffect() {
  paddleWidth *= 2; // 패들 너비 2배 증가
  setTimeout(() => {
    paddleWidth = paddleWidth / 2; // 5초 후 원래 크기로 복원
  }, 5000); // 5초 지속
}

function applyEEffect() {
  EE = true;
}

// 키보드 이벤트 핸들러 추가
$(document).on("keydown", function (e) {
  if (e.key === "q" || e.key === "Q") {
    if (!qEffectUsed) {
      applyQEffect();
      qEffectUsed = true;
      $("#skills img:nth-child(1)").css("filter", "brightness(50%)");
    }
  } else if (e.key === "w" || e.key === "W") {
    if (!wEffectUsed) {
      applyWEffect();
      wEffectUsed = true;
      $("#skills img:nth-child(2)").css("filter", "brightness(50%)");
    }
  } else if (e.key === "e" || e.key === "E") {
    if (!eEffectUsed) {
      applyEEffect();
      eEffectUsed = true;
      $("#skills img:nth-child(3)").css("filter", "brightness(50%)");
    }
  }
});
