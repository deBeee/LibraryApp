-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 28 Lut 2023, 20:12
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `library`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tbook`
--

CREATE TABLE `tbook` (
  `bookid` int(11) NOT NULL,
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ISBN` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `tbook`
--

INSERT INTO `tbook` (`bookid`, `author`, `title`, `ISBN`) VALUES
(1, 'Robert C. Martin', 'Czysty kod', '978-83-832-2344-5'),
(2, 'Rafał Podraza', 'Ścieżka testera', '978-83-283-9802-3'),
(3, 'Adam Roman, Lucjan Stapp', 'Certyfikowany tester ISTQB', '978-83-832-2185-4'),
(4, 'Thomas Nield', 'Podstawy matematyki w data science', '978-83-832-2013-0'),
(5, 'Neal Ford, Mark Richards', 'Złożone zagadnienia architektury oprogramowania', '978-83-283-9527-5'),
(6, 'Tomasz Jaśniewski', 'C++ Zbiór zadań z rozwiązaniami', '978-83-832-2202-8'),
(7, 'Daniel Graham', 'Praktyczne wprowadzenie do hakingu', '978-83-283-9419-3'),
(8, 'Stuart Russell, Peter Norvig', 'Sztuczna inteligencja. Nowe spojrzenie', '978-83-283-7608-3'),
(9, 'Eric Matthes', 'Python. Instrukcje dla programisty', '978-83-283-6360-1'),
(10, 'David Thomas, Andrew Hunt', 'Pragmatyczny programista', '978-83-283-7139-2'),
(19, 'Arkadiusz Brzegowy', 'Tworzenie gier 2D w Unity 2022', '978-83-832-2117-5'),
(20, 'Renée M. P. Teate', 'SQL dla analityków danych', '978-83-283-9744-6'),
(21, 'Ben Forta', 'SQL w mgnieniu oka', '978-83-283-9490-2');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tborrows`
--

CREATE TABLE `tborrows` (
  `borrowid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `borrowdate` date NOT NULL,
  `returned` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `tborrows`
--

INSERT INTO `tborrows` (`borrowid`, `userid`, `bookid`, `name`, `surname`, `borrowdate`, `returned`) VALUES
(1, 1, 3, 'Dominik', 'Bytnar', '2023-02-10', 1),
(2, 2, 4, 'Mateusz', 'Bierny', '2023-02-26', 0),
(3, 5, 9, 'Natalia', 'Kopeć', '2023-02-21', 1),
(5, 5, 2, 'Natalia', 'Kopeć', '2023-02-23', 0),
(16, 5, 20, 'Kacper', 'Okejkowski', '2023-02-28', 0),
(17, 1, 3, 'Kamil', 'Rodzynek', '2023-02-28', 0),
(18, 3, 5, 'Michał', 'Kieliszkowski', '2023-01-10', 0),
(19, 4, 6, 'Maciek', 'Jawowicz', '2023-02-04', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `userid` int(11) NOT NULL,
  `login` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `tuser`
--

INSERT INTO `tuser` (`userid`, `login`, `password`) VALUES
(1, 'dominik', 'fa50c8cc64a073ce3861f86015e7b1d5'),
(2, 'mateusz', '535e314c8242b0722b79ae48b896e3bb'),
(3, 'michal', 'a46bc5032c6e73e9a153599858a9a336'),
(4, 'maciek', 'c25bc3496401124c51c127a395bb2c7c'),
(5, 'natalia', 'f8f82a6aa98053a5eabb3e9fd717db12');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tbook`
--
ALTER TABLE `tbook`
  ADD PRIMARY KEY (`bookid`);

--
-- Indeksy dla tabeli `tborrows`
--
ALTER TABLE `tborrows`
  ADD PRIMARY KEY (`borrowid`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `tbook`
--
ALTER TABLE `tbook`
  MODIFY `bookid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT dla tabeli `tborrows`
--
ALTER TABLE `tborrows`
  MODIFY `borrowid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT dla tabeli `tuser`
--
ALTER TABLE `tuser`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
