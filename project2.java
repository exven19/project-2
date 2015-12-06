// Project 2

String title = "Project 2";
String help  = " Click to shoot cue ball. \n 'r' key to reset, 'q' to quit.";
String news = help;
String author = "Nayeem U. Bhuiyan";

float left, right, up, down;
float middle;

float cueX, cueY, cueDX, cueDY;
float venX, venY, venDX, venDY;
float konX, konY, konDX, konDY;
 float namX, namY, namDX, namDY;

void setup(){
  size(1000,600);
  left = 75;
  right = width-75;
   up = 125;
  down = height-75;
  middle = left + (right - left) /2;
  
  reset();
  news = help + "\n     (+ or - changes brown ball speed.)";
}

void reset(){
  // cue must rest
  cueX = left + (right-left) / 4;
  cueY = up + (down-up) / 2;
  cueDX = cueDY = 0;
  
  venX = random( middle,right); venY = random( up,down);
  konX = random( middle,right); konY = random( up,down);
  namX = random( middle, right); namY = random( up, down);
  
  venDX = random( 1,3 ); venDY = random( 1,3 );
  konDX = random( 1,3 ); konDY = random( 1,3 );
  namDX = random( 1,3 ); namDY = random( 1,3 );
}

void draw(){
  background( 20,100,100 );
  rectMode( CORNERS );
  table( left, up, right, down );
  bounce();
  show();
  messages();
}

void table( float left, float up, float right, float down ){
  fill( 150,150,50 );
  strokeWeight(25);
  stroke(10,10,50);
  rect( left-25, up-25, right+25, down+25 );
  stroke(0);
  strokeWeight(0.5);
}

void bounce(){
  venX += venDX; if ( venX<left || venX>right ) venDX *= -1;
  venY += venDY; if ( venY<up || venY>down ) venDY *= -1;
  
  konX += konDX; if ( konX<left || konX>right ) konDX *= -1;
  konY += konDY; if ( konY<up || konY>down ) konDY *= -1;
  
  namX += namDX; if ( namX<left || namX>right ) namDX *= -1;
  namY += namDY; if ( namY<up || namY>down) namDY *= -1;
  
  cueX += cueDX; if ( cueX<left || cueX>right )  cueDX *= -0.5;
  cueY += cueDY; if ( cueY<up ||cueY>down ) cueDY *= -1;
}

void collisions(){
  float right,left;
  
  if (dist ( venX,venY, konX, konY ) < 30){
    right=konDX; konDX=venDX; venDX=right;
    left=konDY; konDY=venDY; venDY=left;
  }
  
  if (dist (venX,venY,namX,namY ) < 30){
    float namY=namDX; namDX=venDX; venDX=namY;
    float konY=namDY; namDY=venDY; venDY=konY;
  }
  
  if (dist (konX,konY,namX,namY) < 30){
    float venX=namDX; namDX=konDX; konDX=venX;
    float konY=namDY; namDY=konDY; konDY=konY;
  }
  
  float tmp,up,down,venX=0;
  if (dist( cueX, cueY, venX, venY ) <30 ) {
    up=venDX; venDX=cueDX; cueDX=up;
    down=venDY; venDY=cueDY; cueDY=down;
  }
  
  if (dist(cueX,cueY,konX,konY ) <29 ){
    venX=konDX; konDX=cueDX; cueDX=venX;
    venX=konDY; konDY=cueDY; cueDY=venY;
  }
  
  if (dist( cueX,cueY,namX,namY ) <31 ){
    tmp=namDX; namDX=cueDX; cueDX=tmp;
    tmp=namDY; namDY=cueDY; cueDY=tmp;
  }
}

void show(){
  fill( 15,0,0 ); ellipse( venX,venY,30,29.99 );
  fill( 0,255,0 ); ellipse( konX,konY,30,30 );
  fill( 255,0,255 ); ellipse( namX,namY,30,30 );
  fill( 0,255,255 ); ellipse( cueX,cueY,30.07,30 );
}

void messages(){
  fill(0);
  text( title, width/3,20 );
  text( news, width/3, 42 );
  text( author, 10, height-11);
}

void keyPressed(){
  if (key=='r'){
    reset();
  }
  if (key == '+') { venDX *=2; venDY *=2; }
  if (key == '-') { venDX /=2; venDY /=2; }
}

void mousePressed(){
  float force = dist( mouseX, mouseY, cueX, cueY ) / 20;
   strokeWeight( force );
   line( mouseX,mouseY,cueX,cueY );
   strokeWeight(1);
   
   cueDX = ( cueX-mouseX ) / 28.9;
   cueDY = ( cueY-mouseY ) / 31.2;
}
