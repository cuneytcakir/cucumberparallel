Feature: VGP Main Page

  @smoke
  Scenario: Kullanici secerek sisteme login olma
    Given VGP anasayfa acilir
    When bir kullanici secilir
    Then uygulama anasayfasina gittigi gorulur

