%%
%% This is file `mitthesis.cls',
%% generated with the docstrip utility.
%%
%% The original source files were:
%%
%% ubcthesis.dtx  (with options: `mitthesis')
%% 
%% This file was generated from the ubcthesis package.
%% --------------------------------------------------------------
%% 
%% Copyright (C) 2001
%% Michael McNeil Forbes
%% mforbes@alum.mit.edu
%% 
%% This file may be distributed and/or modified under the
%% conditions of the LaTeX Project Public License, either version 1.2
%% of this license or (at your option) any later version.
%% The latest version of this license is in
%%    http://www.latex-project.org/lppl.txt
%% and version 1.2 or later is part of all distributions of LaTeX
%% version 1999/12/01 or later.
%% 
%% This program consists of the files ubcthesis.dtx, ubcthesis.ins, and
%% the sample figures fig.eps and fig.fig.
%% 
%% This file may only be distributed together with a copy of this
%% program. You may, however, distribute this program without generated
%% files such as this one.
%% 


\NeedsTeXFormat{LaTeX2e}[1995/12/01]
\ProvidesClass{mitthesis}[2006/09/02 v1.42 ^^J
 Massachusetts Institute of Technology Thesis Class]
\LoadClassWithOptions{genthesis}
\ProcessOptions
\providecommand*{\ESPOL}{ESCUELA SUPERIOR POLIT\'ECNICA DEL LITORAL}
\providecommand*{\MIT}{MASSACHUSETTS INSTITUTE OF TECHNOLOGY}
\providecommand*{\Mit}{Massachusetts Institute of Technology}
\providecommand*{\prevdegrees}[1]{\previousdegrees{#1}}
\providecommand*{\thesisdate}[1]{\date{#1}}
\providecommand*{\chairman}[2]{\departmentchair{#1}\departmentchairtitle{#2}}
\newcommand{\@abstractsupervisor}{}
\providecommand*{\supervisor}[2]{%
  \addsignature{Certified by}{#1 \\ #2 \\ DIRECTOR DE TESIS}
  \@addto{@abstractsupervisor}{DIRECTOR DE TESIS: #1\par%
    Title: #2\par}
}
\newcommand*{\and}{\degreeword{degrees} \par and \par}
\newcommand*{\degreeword}[1]{\gdef\@degreeword{#1}}
\providecommand*{\@degreeword}{%
  \ClassWarning{mitthesis}{No \noexpand\degreeword given}}
\AtBeginDocument{
  \degreeword{degree}
  \institution{ESCUELA SUPERIOR POLIT\'ECNICA DEL LITORAL}
  \institutionaddress{GUAYAQUIL  ECUADOR}
  \logofile{logo.eps}
}
\renewcommand{\titlepage}{
  \begin{center}
  {\includegraphics[width=4.82cm,height=4.76cm]{\@logofile}\par}
  \vspace{3em plus 2fil minus 1em}%
  {\institutionfont\@condupper{@upperinstitution}{\@institution}\par}%
  \vspace{2em plus 2fil minus 1em}%
  {\facultyfont\@condupper{@upperfaculty}{\uline{FACULTAD DE INGENIER\'IA EN ELECTRICIDAD Y COMPUTACI\'ON}}}\par%
  \vspace{2em plus 2fil minus 1em}%
  {\titlefont{PROYECTO DE GRADO} \par}%
  \vspace{2em plus 2fil minus 1em}%
  {\titlefont\@condupper{@uppertitle}{\@title}}\par%
  \vspace{2em plus 2fil minus 1em}%
  {\degreetitlefont\@condupper{@uppertitletext}{Previa a la Obtenci\'on del T\'itulo de \@degreetitle}\par}%
  \vspace{2em plus 2fil minus 1em}%
  {\authorfont
    \@condupper{@upperauthor}{PRESENTADA POR:\par\@author}\par%
  }%
  \vspace{6em plus 6fil minus 1.5em}%
  {\bfseries\@institutionaddress\par}%
  \vspace{3em plus 2fil minus 1em}%
  {\bfseries\@copyrightyear\par}%
  \end{center}
}
\renewenvironment{abstract}{
  \cleardoublepage
  \@startonecolumn
\setlength{\parskip}{\baselineskip}

%  \thispagestyle{empty}
  {
    \def\@tempname{\@toupper{\abstractname}}
    \section*{\uline{\@tempname}}
%    \@mkboth{\@tempname}{\@tempname}
    \addcontentsline{toc}{chapter}{\@toctoupper{\abstractname}}
  }
}
{
  \par%
  \vspace{1em}
  \vfil
  \setlength{\parindent}{0pt}
  \@abstractsupervisor
  \vspace{0pt plus 2fil}
  \clearpage
  \@endonecolumn
}
\newcommand{\hangpara}{
 \setlength{\parindent}{1.5cm}
 \hangindent=1.5cm % indent all subsequent lines
}
\newenvironment{indentar}
{\begin{list}{}%
         {\setlength{\parsep}{\baselineskip}}%
         {\setlength{\leftmargin}{1.50cm}}%
         \item[]%
}
{\end{list}}
\endinput

%%
%% End of file `mitthesis.cls'.
