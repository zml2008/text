/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2020 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.text.feature.bossbar;

import net.kyori.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

// TODO: "on change" notifications for implementation to know something changed
public abstract class AbstractBossBar implements BossBar {
  private Component name;
  private float percent;
  private Color color;
  private Overlay overlay;
  private boolean darkenScreen;
  private boolean playBossMusic;
  private boolean createWorldFog;

  protected AbstractBossBar(final Component name, final float percent, final Color color, final Overlay overlay) {
    this.name = name;
    this.percent = percent;
    this.color = color;
    this.overlay = overlay;
  }

  @Override
  public @NonNull Component name() {
    return this.name;
  }

  @Override
  public @NonNull BossBar name(final @NonNull Component name) {
    this.name = name;
    return this;
  }

  @Override
  public float percent() {
    return this.percent;
  }

  @Override
  public @NonNull BossBar percent(final float percent) {
    this.percent = percent;
    return this;
  }

  @Override
  public @NonNull Color color() {
    return this.color;
  }

  @Override
  public @NonNull BossBar color(final @NonNull Color color) {
    this.color = color;
    return this;
  }

  @Override
  public @NonNull Overlay overlay() {
    return this.overlay;
  }

  @Override
  public @NonNull BossBar overlay(final @NonNull Overlay overlay) {
    this.overlay = overlay;
    return this;
  }

  @Override
  public boolean darkenScreen() {
    return this.darkenScreen;
  }

  @Override
  public @NonNull BossBar darkenScreen(final boolean darkenScreen) {
    this.darkenScreen = darkenScreen;
    return this;
  }

  @Override
  public boolean playBossMusic() {
    return this.playBossMusic;
  }

  @Override
  public @NonNull BossBar playBossMusic(final boolean playBossMusic) {
    this.playBossMusic = playBossMusic;
    return this;
  }

  @Override
  public boolean createWorldFog() {
    return this.createWorldFog;
  }

  @Override
  public @NonNull BossBar createWorldFog(final boolean createWorldFog) {
    this.createWorldFog = createWorldFog;
    return this;
  }
}
