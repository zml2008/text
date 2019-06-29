/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2019 KyoriPowered
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
package net.kyori.text.renderer;

import java.util.List;
import java.util.Optional;
import net.kyori.text.BlockNbtComponent;
import net.kyori.text.BuildableComponent;
import net.kyori.text.Component;
import net.kyori.text.ComponentBuilder;
import net.kyori.text.EntityNbtComponent;
import net.kyori.text.KeybindComponent;
import net.kyori.text.ScoreComponent;
import net.kyori.text.SelectorComponent;
import net.kyori.text.TextComponent;
import net.kyori.text.TranslatableComponent;
import net.kyori.text.event.HoverEvent;
import org.checkerframework.checker.nullness.qual.NonNull;

public abstract class AbstractDeepComponentRenderer<C> extends AbstractComponentRenderer<C> {
  @Override
  protected @NonNull Component render(final @NonNull BlockNbtComponent component, @NonNull final C context) {
    final BlockNbtComponent.Builder builder = BlockNbtComponent.builder()
      .nbtPath(component.nbtPath())
      .interpret(component.interpret())
      .pos(component.pos());
    return this.deepRender(component, builder, context);
  }

  @Override
  protected @NonNull Component render(final @NonNull EntityNbtComponent component, @NonNull final C context) {
    final EntityNbtComponent.Builder builder = EntityNbtComponent.builder()
      .nbtPath(component.nbtPath())
      .interpret(component.interpret())
      .selector(component.selector());
    return this.deepRender(component, builder, context);
  }

  @Override
  protected @NonNull Component render(final @NonNull KeybindComponent component, @NonNull final C context) {
    final KeybindComponent.Builder builder = KeybindComponent.builder(component.keybind());
    return this.deepRender(component, builder, context);
  }

  @Override
  protected @NonNull Component render(final @NonNull ScoreComponent component, @NonNull final C context) {
    final ScoreComponent.Builder builder = ScoreComponent.builder()
      .name(component.name())
      .objective(component.objective())
      .value(component.value());
    return this.deepRender(component, builder, context);
  }

  @Override
  protected @NonNull Component render(final @NonNull SelectorComponent component, @NonNull final C context) {
    final SelectorComponent.Builder builder = SelectorComponent.builder(component.pattern());
    return this.deepRender(component, builder, context);
  }

  @Override
  protected @NonNull Component render(final @NonNull TextComponent component, @NonNull final C context) {
    final TextComponent.Builder builder = TextComponent.builder(component.content());
    return this.deepRender(component, builder, context);
  }

  @Override
  protected @NonNull Component render(final @NonNull TranslatableComponent component, @NonNull final C context) {
    final TranslatableComponent.Builder builder = TranslatableComponent.builder(component.key());
    final List<Component> oldArgs = component.args();
    final List<Component> newArgs = component.args();
    for(int i = 0; i < oldArgs.size(); i++) {
      newArgs.add(this.render(oldArgs.get(i), context));
    }
    builder.args(newArgs);
    return this.deepRender(component, builder, context);
  }

  protected final <O extends BuildableComponent<O, B>, B extends ComponentBuilder<O, B>> O deepRender(final @NonNull Component component, final @NonNull B builder, final @NonNull C context) {
    this.mergeStyle(component, builder, context);
    component.children().forEach(child -> builder.append(this.render(child, context)));
    return builder.build();
  }

  protected  <B extends ComponentBuilder<?, ?>> void mergeStyle(final Component component, final B builder, final C context) {
    builder.mergeColor(component);
    builder.mergeDecorations(component);
    builder.clickEvent(component.clickEvent());
    Optional.ofNullable(component.hoverEvent()).ifPresent(hoverEvent -> {
      builder.hoverEvent(HoverEvent.of(
        hoverEvent.action(),
        this.render(hoverEvent.value(), context)
      ));
    });
  }
}
